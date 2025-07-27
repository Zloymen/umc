package ru.mts.media.platform.umc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = """
            spring.config.name=application,kafka,postgres,graphql
            """)
@ActiveProfiles("test")
public abstract class JupiterTest {
}
