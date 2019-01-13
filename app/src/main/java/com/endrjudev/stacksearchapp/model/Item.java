package com.endrjudev.stacksearchapp.model;

import com.squareup.moshi.Json;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Item {
    @Json(name = "tags")
    private List<String> tags;
    @Json(name = "owner")
    private Owner owner;
    @Json(name = "is_answered")
    private boolean isAnswered;
    @Json(name = "view_count")
    private int viewCount;
    @Json(name = "answer_count")
    private int answerCount;
    @Json(name = "score")
    private int score;
    @Json(name = "last_activity_date")
    private String lastActivityDate;
    @Json(name = "creation_date")
    private String creationDate;
    @Json(name = "last_edit_date")
    private String lastEditDate;
    @Json(name = "question_id")
    private int questionId;
    @Json(name = "link")
    private String link;
    @Json(name = "title")
    private String title;
}
