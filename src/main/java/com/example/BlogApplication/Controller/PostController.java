  package com.example.BlogApplication.Controller;

import com.example.BlogApplication.Config.AppConstant;
import com.example.BlogApplication.Payload.PostDto;
import com.example.BlogApplication.Responce.ApiResponse;
import com.example.BlogApplication.Responce.PostResponse;
import com.example.BlogApplication.Service.FileService;
import com.example.BlogApplication.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    private PostService postService;

    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @Autowired
    public PostController(PostService postService, FileService fileService) {
        this.postService = postService;
        this.fileService = fileService;
    }

    @PostMapping("/{userId}/{categoryId}/save")
    public ResponseEntity<?> CreatePost(@RequestBody PostDto postDto,
                                        @PathVariable long userId,
                                        @PathVariable long categoryId)throws IOException
    {
        PostDto post = postService.CreatePost(postDto, userId, categoryId);
        return  new ResponseEntity<>(post, HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> GetPostByUser(@PathVariable long userId)
    {
        List<PostDto> postDtos = postService.FindPostByUser(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.FOUND);

    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> GetPostByCategory(@PathVariable long categoryId)
    {
        List<PostDto> postDtos = postService.FindPostByCategory(categoryId);
        return new ResponseEntity<>(postDtos, HttpStatus.FOUND);
    }
    @PutMapping("/Update/{postId}")
    public ResponseEntity<PostDto> UpdatePost(@RequestBody PostDto postDto,long postId)
    {
        PostDto postDto1 = postService.UpdatePost(postDto, postId);
        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<PostResponse> GetAllPost(@RequestParam(value ="pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
                                                   @RequestParam(value ="pageSize",defaultValue =AppConstant.PAGE_SIZE,required = false) Integer pageSize ,
                                                   @RequestParam(value="sort",defaultValue=AppConstant.SORTING,required = false) String sort,
                                                   @RequestParam(value ="direction",defaultValue =AppConstant.ORDER,required = false) String direction)
    {
        PostResponse postDtos= postService.GetAllPost(pageNumber,pageSize,sort,direction);
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
    @GetMapping("postId")
    public ResponseEntity<?> GetPostById(long postId)
    {
        PostDto postDto = postService.GetById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.FOUND);
    }
    @GetMapping("/delete/{postId}")
    public ResponseEntity<?> DeletePost(long postId)
    {
        postService.DeletePost(postId);
        return ResponseEntity.ok(new ApiResponse("POST DELETED SUCCESSFULLY",true));
    }
    @GetMapping("/{Title}")
    public ResponseEntity<List<PostDto>> Search(String Title)
    {
        List<PostDto> search = postService.Search(Title);
        return new ResponseEntity<>(search, HttpStatus.OK);
    }

    @PostMapping("/image/{id}")
    public ResponseEntity<PostDto> UploadImage(@RequestParam("image")MultipartFile image,@PathVariable long id) throws IOException {
        PostDto postDto = postService.GetById(id);
        String upload = fileService.upload(path, image);
        postDto.setImageNme(upload);
        PostDto postDto1 = postService.UpdatePost(postDto, id);

        if (image != null && !image.isEmpty()) {
            File tempFile = new File(image.getOriginalFilename());
            if (tempFile.exists() && !tempFile.delete()) {
                // Log or handle if the temporary file could not be deleted
                System.out.println("Failed to delete temporary file.");
            }
        }


        return new ResponseEntity<>(postDto1, HttpStatus.OK);
    }
}
