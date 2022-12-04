package com.project.robotmate.domain.entity;

import com.project.robotmate.core.types.BoardType;
import com.project.robotmate.core.types.GalleryType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "RM_GALLERY")
@ToString(of = {"id", "title", "contents", "delYn", "type"})
public class Gallery extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String title;

    private String contents;

    @Column(name = "del_yn")
    private String delYn;

    @Column(name = "gall_type")
    @Enumerated(EnumType.STRING)
    private GalleryType type;

    @Column(name = "year")
    private String year;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Admin admin;

    @Builder
    public Gallery(String title, String contents, String delYn, GalleryType type, Admin admin) {
        this.title = title;
        this.contents = contents;
        this.delYn = delYn;
        this.type = type;
        this.admin = admin;
    }
}
