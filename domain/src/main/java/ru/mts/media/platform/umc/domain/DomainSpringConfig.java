package ru.mts.media.platform.umc.domain;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.scalars.ExtendedScalars;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class DomainSpringConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(localDateTimeScalar())
                .scalar(ExtendedScalars.GraphQLLong);
    }

    @Bean
    public GraphQLScalarType localDateTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("Java 8 LocalDateTime")
                .coercing(new Coercing<LocalDateTime, String>() {
                    @Override
                    public String serialize(Object input, GraphQLContext graphQLContext, Locale locale) {
                        // Преобразование LocalDateTime в строку
                        if (input instanceof LocalDateTime inputLocalDateTime) {
                            return inputLocalDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        }
                        throw new CoercingSerializeException("Not a valid LocalDateTime");
                    }
                    @Override
                    public LocalDateTime parseValue(Object input, GraphQLContext graphQLContext, Locale locale) {
                        // Парсинг строки в LocalDateTime (для переменных)
                        if (input instanceof String inputString) {
                            return LocalDateTime.parse(inputString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        }
                        throw new CoercingParseValueException("Not a valid LocalDateTime string");
                    }
                    @Override
                    public LocalDateTime parseLiteral(@NonNull Value<?> input,
                                                                @NonNull CoercedVariables variables,
                                                                @NonNull GraphQLContext graphQLContext,
                                                                @NonNull Locale locale)
                            throws CoercingParseLiteralException {

                        if (input instanceof StringValue inputStringValue) {
                            return LocalDateTime.parse(inputStringValue.getValue(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        }
                        throw new CoercingParseLiteralException("Not a valid LocalDateTime literal");
                    }

                })
                .build();
    }
}
