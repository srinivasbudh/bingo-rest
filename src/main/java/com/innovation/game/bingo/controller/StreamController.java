package com.innovation.game.bingo.controller;


import com.innovation.game.bingo.service.ResultService;
import com.innovation.game.bingo.util.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bingo-rest/bingo/")
public class StreamController {


    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "getYouTubeLink", method = RequestMethod.GET)
    public String getLink() {
        return resultService.getLink();
    }


    @RequestMapping(value = "setYouTubeLink", method = RequestMethod.GET)
    public String setLink(@RequestParam String liveStreamURL,String authCode) {
        PasswordAuthentication p= new PasswordAuthentication();
        if(p.authenticate(authCode.toUpperCase(),"$31$16$X61QdNBp6bDo5yiwTByklvRpqXy3HUuQrZu0vlR2gAQ")) {
            return resultService.addYoutubeLink(liveStreamURL);
        }else{
            return "Sorry you are not Authorized to perform this operation";
        }
    }

    @RequestMapping(value = "deleteLink", method = RequestMethod.GET)
    public boolean remove(String authCode) {
        PasswordAuthentication p= new PasswordAuthentication();
        if(p.authenticate(authCode.toUpperCase(),"$31$16$X61QdNBp6bDo5yiwTByklvRpqXy3HUuQrZu0vlR2gAQ")) {
            return resultService.removeLink();
        }else{
            return false;
        }

    }


}
