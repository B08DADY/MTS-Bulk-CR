package com.mts.bulkvalidation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WF_WORK")
@Getter
@Setter
@NoArgsConstructor
public class WfWork {

    @Id
    @Column(name = "WORK_ID")
    private Long workId;

    @Column(name = "INSTANCE_ID")
    private Long instanceId;

    @Column(name = "STATUS")
    private String status;


}