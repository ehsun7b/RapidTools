package ehsun7b.github.RapidTools.config;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class ShellConfiguration implements PromptProvider {
    @Override
    public AttributedString getPrompt() {
        return new AttributedString("Rapid:>");
    }
}
