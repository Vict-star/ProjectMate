package com.example.projectmate.controller;

import com.example.projectmate.domain.Match;
import com.example.projectmate.service.MatchService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchService matchService;
    //显示比赛列表
    @RequestMapping(value = "/MatchesList")
    public @ResponseBody
    List<Match> MatchesList() throws IOException {
        List<Match> matchList=matchService.ListAllMatches();
        return matchList;
    }
    //显示比赛详情
    @RequestMapping(value = "/MatchesDetails")
    public @ResponseBody
    Match MatchesDetails(@RequestBody String MatchId) throws IOException {
        Match match=new Match();
        JSONObject jsonObject = JSONObject.fromObject(MatchId);
        String matchid = jsonObject.getString("match_id");
        int match_id = Integer.parseInt(matchid);
        Match match1=matchService.findMatchById(match_id);
        if (match1!=null){
            match=match1;
        }
        return match;
    }
}
