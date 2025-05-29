package dasturlash.uz.dto.profile;

import dasturlash.uz.enums.ProfileRole;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProfileDTO {
    private Integer id;

    @NotBlank(message = "Ism bo‘sh bo‘lmasligi kerak")
    private String name;

    @NotBlank(message = "Familiya bo‘sh bo‘lmasligi kerak")
    private String surname;

    @NotBlank(message = "Username bo‘sh bo‘lmasligi kerak")
    private String phone;

    @Email(message = "Email noto‘g‘ri formatda bo'lishi mumkin")
    private String email;

    @NotBlank(message = "Parol bo‘sh bo‘lmasligi kerak")
    private String password;

    @NotNull(message = "Visible qiymati null bo‘lmasligi kerak")
    private Boolean visible;

    private LocalDateTime createdDate;

    private String photoId;

    @NotBlank(message = "Parol bo'sh bo'lmasligi kerak")
    List<ProfileRole> roleList;


}
