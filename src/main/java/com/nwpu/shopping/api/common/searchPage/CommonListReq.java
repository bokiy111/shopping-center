package com.nwpu.shopping.api.common.searchPage;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Component
@SuperBuilder
public class CommonListReq {
    @Range(min = 1, max = Integer.MAX_VALUE, message = "页码数不合法，需要在正整型范围内")
    @NotNull(message = "页码数非空")
    private Integer current = 1;
    @Range(min = 1, max = Integer.MAX_VALUE, message = "数据量大小不合法，需要在正整型范围内")
    @NotNull(message = "数据量不可为空")
    private Integer pageSize = 20;
}
