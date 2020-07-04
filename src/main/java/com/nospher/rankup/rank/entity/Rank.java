package com.nospher.rankup.rank.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@Getter
public class Rank {

    private final String name;
    private final Integer hierarchy;
    private final String prefix;
    private final Double price;
    private final Double points;
    private final List<String> commands;

}
