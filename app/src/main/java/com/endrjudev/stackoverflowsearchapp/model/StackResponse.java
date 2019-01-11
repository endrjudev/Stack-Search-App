package com.endrjudev.stackoverflowsearchapp.model;

import com.squareup.moshi.Json;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StackResponse {
    @Json(name = "items")
    private List<Item> items;
}
