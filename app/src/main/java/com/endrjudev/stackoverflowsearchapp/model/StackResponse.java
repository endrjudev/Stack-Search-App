package com.endrjudev.stackoverflowsearchapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StackResponse extends BaseResponse {
    private List<Item> items;
}
