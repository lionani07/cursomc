package cursomc.resources.utils;

import lombok.experimental.UtilityClass;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class URL {

    public String decodeParam(String param) {
        return URLDecoder.decode(param, StandardCharsets.UTF_8);
    }

    public List<Integer> convertFrom(String stringIds) {
        return Arrays.stream(stringIds.split(Pattern.quote(",")))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
