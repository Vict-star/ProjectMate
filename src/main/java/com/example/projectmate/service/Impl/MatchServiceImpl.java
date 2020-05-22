package com.example.projectmate.service.Impl;

import com.example.projectmate.dao.MatchDao;
import com.example.projectmate.domain.Match;
import com.example.projectmate.service.MatchService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchDao matchDao;

    @Override
    public List<Match> ListAllMatches(){return matchDao.ListAllMatches();}
    @Override
    public Match findMatchById(int match_id){return matchDao.findMatchById(match_id);}
}
