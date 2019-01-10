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
public class Item {
    @Json(name = "tags")
    private List<String> tags;
    @Json(name = "owner")
    private Owner owner;
    @Json(name = "is_answered")
    private Boolean isAnswered;
    @Json(name = "view_count")
    private Integer viewCount;
    @Json(name = "answer_count")
    private Integer answerCount;
    @Json(name = "score")
    private Integer score;
    @Json(name = "last_activity_date")
    private String lastActivityDate;
    @Json(name = "creation_date")
    private String creationDate;
    @Json(name = "last_edit_date")
    private String lastEditDate;
    @Json(name = "question_id")
    private Integer questionId;
    @Json(name = "link")
    private String link;
    @Json(name = "title")
    private String title;
}
