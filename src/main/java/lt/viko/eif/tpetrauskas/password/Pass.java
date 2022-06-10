package lt.viko.eif.tpetrauskas.password;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pass {
    private String name;
    private String password;
    private String app;
    private String comment;

    @Override
    public String toString() {
        return "{" +
                "naudotojo vardas = '" + name + '\'' +
                ", naudotojo slaptazodis = '" + password + '\'' +
                ", programa = '" + app + '\'' +
                ", komentaras = '" + comment + '\'' +
                '}';
    }
}
