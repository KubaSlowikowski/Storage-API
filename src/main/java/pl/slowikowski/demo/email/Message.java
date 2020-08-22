package pl.slowikowski.demo.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slowikowski.demo.export.ExportDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    @NotBlank(message = "Message's subject must not be null or empty")
    private String subject;
    private String text;
    @Email
    private String sendFrom = "ttpraktyki2020storage@gmail.com"; //FIXME
    @NotBlank(message = "Message receiver must not be null or empty")
    @Email
    private String sendTo;
    private ExportDto file;
    private String fileExtension;

}
