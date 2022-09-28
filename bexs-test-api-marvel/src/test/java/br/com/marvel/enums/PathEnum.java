package br.com.marvel.enums;

import lombok.Getter;

import java.util.Objects;

public enum PathEnum {

    PATH_CHARACTER_ID("/v1/public/characters/{characterId}"),
    PATH_STORIES("/v1/public/stories");

    @Getter
    private String path;

    PathEnum( String path ) {
        this.path = path;
    }

    public static String getPath( String path ) throws RuntimeException {

        if( Objects.isNull( path ) )
            throw new RuntimeException( path);
        for( PathEnum r : PathEnum.values() ) {
            if( path.equals( r.name() ) )
                return r.getPath();
        }
        throw new RuntimeException( path);
    }
}
