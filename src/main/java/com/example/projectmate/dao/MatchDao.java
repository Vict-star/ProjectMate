package com.example.projectmate.dao;

import com.example.projectmate.domain.Match;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MatchDao {
    List<Match> ListAllMatches();
    Match findMatchById(int match_id);
}
