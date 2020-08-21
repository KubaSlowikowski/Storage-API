package pl.slowikowski.demo.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.slowikowski.demo.export.ExportDto;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private String subject;
    private String text;
    private String sendFrom = "ttpraktyki2020storage@gmail.com"; //FIXME
    private String sendTo;
    private ExportDto file;
    private String fileExtension;

}
