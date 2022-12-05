package com.example.w2.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    public String mid;
    public String mpw;
    public String mname;
    public String uuid;
}
