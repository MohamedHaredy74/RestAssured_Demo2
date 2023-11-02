package utils.testData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthData {
    String username ;String password;
}
