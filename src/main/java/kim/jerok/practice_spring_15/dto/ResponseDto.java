package kim.jerok.practice_spring_15.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

    private String msg;
    private T data;

    public ResponseDto(String msg) {
        this.msg = msg;
    }

    public ResponseDto(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

}
