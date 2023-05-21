package com.example.application.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.ProblemDetail;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Errors {

    public static ProblemDetail ofBadRequest(String detail) {
        var pd = ProblemDetail.forStatus(400);
        pd.setDetail(detail);
        return pd;
    }

}
