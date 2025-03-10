package az.idrak.appv1.entity.user;
//package az.idrak.appV1.entity;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.ManyToAny;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import az.idrak.appV1.entity.User;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.SequenceGenerator;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@SequenceGenerator(name = "seq_user", sequenceName = "seq_user_id", allocationSize = 1, initialValue = 1)
//public class Role {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//
//    @Column(unique=true)
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    @JsonIgnore
//    private List<User> users;
//
//    @CreationTimestamp
//    @Column(updatable = false, nullable=false)
//    private LocalDateTime createdDate;
//
//    @LastModifiedDate
//    @Column(insertable=false, nullable=false)
//    private LocalDateTime updatedDate;
//}
