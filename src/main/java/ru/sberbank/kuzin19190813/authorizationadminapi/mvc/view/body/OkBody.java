package ru.sberbank.kuzin19190813.authorizationadminapi.mvc.view.body;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OkBody implements Body {
    String message;

    @Override
    public String toString() {
        return String.format("{\"message\":\"%s\"}", message);
    }
}
