package org.example.entity;
import jakarta.persistence.*;

@Entity
public class Certificate {
    @Id @GeneratedValue
    @Column(name = "CERTIFICATE_ID")
    private long certificateId;

    @Column(name = "CERTIFICATE_NAME")
    private String certificateName;
    

    public long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(long certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

}
