package com.example.projectmate.service;

import com.example.projectmate.domain.Match;
import java.util.List;

public interface MatchService {
    List<Match> ListAllMatches();
    Match findMatchById(int match_id);
}
