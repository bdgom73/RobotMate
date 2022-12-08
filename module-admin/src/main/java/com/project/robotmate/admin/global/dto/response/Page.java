package com.project.robotmate.admin.global.dto.response;

import lombok.Getter;

@Getter
public class Page<T> {

    private Pageable pageable;

    private T contents;

    public Page(Pageable pageable, T contents) {
        this.pageable = pageable;
        this.contents = contents;
    }
}
