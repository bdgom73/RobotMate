package com.project.robotmate.domain.entity;

import com.project.robotmate.core.types.BoardType;
import com.project.robotmate.core.types.TargetType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "RM_FILE")
@ToString
public class File extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private TargetType type;

    @Column(length = 500)
    private String bucket;

    @Column(name = "ori_file_nm", length = 500)
    private String originalFileName;

    @Column(name = "nm", length = 500)
    private String name;

    @Column(name = "file_format", length = 50)
    private String fileFormat;

    private int size;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "display_order")
    private int displayOrder;

    @Column(name = "del_yn")
    private String delYn;

    @Builder
    public File(Long id, TargetType type, String bucket, String originalFileName, String name, String fileFormat, int size, Long targetId, int displayOrder, String delYn) {
        this.id = id;
        this.type = type;
        this.bucket = bucket;
        this.originalFileName = originalFileName;
        this.name = name;
        this.fileFormat = fileFormat;
        this.size = size;
        this.targetId = targetId;
        this.displayOrder = displayOrder;
        this.delYn = delYn;
    }
}
