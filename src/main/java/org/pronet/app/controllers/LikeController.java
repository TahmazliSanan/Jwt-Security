package org.pronet.app.controllers;

import lombok.RequiredArgsConstructor;
import org.pronet.app.entities.Like;
import org.pronet.app.requests.like.LikeCreateRequest;
import org.pronet.app.responses.LikeResponse;
import org.pronet.app.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/like")
public class LikeController {
    private final LikeService likeService;

    @PostMapping(value = "/create")
    public Like createLike(@RequestBody LikeCreateRequest request) {
        return likeService.createLike(request);
    }

    @GetMapping(value = "/list")
    public List<LikeResponse> getLikeList(
            @RequestParam Optional<Long> userId,
            @RequestParam Optional<Long> postId) {
        return likeService.getLikeList(userId, postId);
    }

    @GetMapping(value = "/details/{likeId}")
    public LikeResponse getLikeById(@PathVariable(value = "likeId") Long likeId) {
        return likeService.getLikeById(likeId);
    }

    @DeleteMapping(value = "/delete/{likeId}")
    public void deleteLike(@PathVariable(value = "likeId") Long likeId) {
        likeService.deleteLike(likeId);
    }
}
