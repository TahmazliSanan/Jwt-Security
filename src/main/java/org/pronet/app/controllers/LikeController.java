package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Like;
import org.pronet.app.requests.like.LikeCreateRequest;
import org.pronet.app.responses.LikeResponse;
import org.pronet.app.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping(value = "/create")
    public ResponseEntity<Like> createLike(@RequestBody LikeCreateRequest request) {
        Like createdLike = likeService.createLike(request);
        return new ResponseEntity<>(createdLike, HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<LikeResponse>> getLikeList(
            @RequestParam Optional<Long> userId,
            @RequestParam Optional<Long> postId) {
        List<LikeResponse> likeList = likeService.getLikeList(userId, postId);
        return new ResponseEntity<>(likeList, HttpStatus.OK);
    }

    @GetMapping(value = "/details/{likeId}")
    public ResponseEntity<LikeResponse> getLikeById(@PathVariable(value = "likeId") Long likeId) {
        LikeResponse foundedLike = likeService.getLikeById(likeId);
        if (foundedLike != null) {
            return new ResponseEntity<>(foundedLike, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable(value = "likeId") Long likeId) {
        Boolean existLikeById = likeService.existsById(likeId);
        if (existLikeById) {
            likeService.deleteLike(likeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
