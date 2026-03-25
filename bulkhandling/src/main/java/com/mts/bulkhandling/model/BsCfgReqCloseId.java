package com.mts.bulkhandling.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BsCfgReqCloseId implements Serializable {

    @Column(name = "REQUEST_TYPE", length = 20, nullable = false)
    private String requestType;

    @Column(name = "CLOSE_CODE", length = 20, nullable = false)
    private String closeCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BsCfgReqCloseId)) return false;
        BsCfgReqCloseId that = (BsCfgReqCloseId) o;
        return Objects.equals(requestType, that.requestType) &&
                Objects.equals(closeCode, that.closeCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestType, closeCode);
    }
}
