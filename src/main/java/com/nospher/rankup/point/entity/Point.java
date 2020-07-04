package com.nospher.rankup.point.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.material.MaterialData;

/**
 * @author oNospher
 **/
@RequiredArgsConstructor
@Getter
public class Point {

    private final MaterialData materialData;
    private final Double value;

}
