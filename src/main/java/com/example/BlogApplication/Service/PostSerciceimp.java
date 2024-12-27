package com.example.BlogApplication.Service;


import com.example.BlogApplication.Entity.Category;
import com.example.BlogApplication.Entity.Post;
import com.example.BlogApplication.Entity.User;
import com.example.BlogApplication.Exception.ResourceNotFoundException;
import com.example.BlogApplication.Payload.PostDto;
import com.example.BlogApplication.Repositor.CategoryRepository;
import com.example.BlogApplication.Repositor.PostRepository;
import com.example.BlogApplication.Repositor.UserRepository;
import com.example.BlogApplication.Responce.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PostSerciceimp implements PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public PostSerciceimp(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto CreatePost(PostDto postDto , long UserId, long CategoryId) {
        User user = userRepository.findById(UserId).orElseThrow(()->new ResourceNotFoundException("User","userId",UserId));

        Category category = categoryRepository.findById(CategoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",CategoryId));

        Post post = modelMapper.map(postDto, Post.class);
        post.setImageNme("default.png");
        post.setAddeddate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post save = postRepository.save(post);
        return modelMapper.map(save,PostDto.class);
    }
    @CachePut(value ="Post",key ="#postId")
    @Override
    public PostDto UpdatePost(PostDto postDto,long postId) {

    Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
    post.setTitle(postDto.getTitle());
    post.setContent(postDto.getContent());
    post.setImageNme(postDto.getImageNme());
        return this.modelMapper.map(post,PostDto.class);
    }
    @Cacheable(value ="Post",key ="#postDto")
    @Override
    public PostDto GetById(long postDto) {
        Post post = postRepository.findById(postDto).orElseThrow(()->new ResourceNotFoundException("POST","postId",postDto));
        return this.modelMapper.map(post,PostDto.class) ;
    }

    @Override
    public PostResponse GetAllPost(Integer PageNumber, Integer PageSize,String SortBy,String direction) {
        Sort sort=null;
        if(direction.equalsIgnoreCase("ASC"))
        {
            sort=Sort.by(SortBy).ascending();
        }else {
            sort=Sort.by(SortBy).descending();
        }
        Pageable pageable=PageRequest.of(PageNumber,PageSize,sort);
        Page<Post> PostList = postRepository.findAll(pageable);
        List<Post> content = PostList.getContent();

        List<PostDto> postList = content.stream().map((dto) -> this.modelMapper.map(dto, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse(postList, PostList.getNumber(), PostList.getSize(), PostList.getTotalPages(), PostList.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> FindPostByUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USER","userId",userId));
        List<Post> postByUser = postRepository.findPostByUser(user);

        List<PostDto> PostByuser = postByUser.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return PostByuser;
    }

    @Override
    public List<PostDto> FindPostByCategory(long categoryId) {
        Category byId = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("CATEGORY","categoryId",categoryId));
        List<Post> postByCategory = postRepository.findPostByCategory(byId);

       List<PostDto> postCategory= postByCategory.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postCategory;
    }
    @CacheEvict(value ="Post",key ="#postId")
    @Override
    public void DeletePost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));

        postRepository.delete(post);
    }

    @Override
    public List<PostDto> Search(String Title) {

        List<Post> byPostTitle = postRepository.findByTitleContaining(Title);
        List<PostDto> postDtoStream = byPostTitle.stream().map((dto) -> this.modelMapper.map(dto, PostDto.class)).collect(Collectors.toList());
        return postDtoStream;
    }
}
