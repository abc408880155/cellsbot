package com.zemise.cellsbot.common.plugin.mcping.bc.chat;

import com.google.gson.*;
import com.zemise.cellsbot.common.plugin.mcping.bc.BaseComponent;
import com.zemise.cellsbot.common.plugin.mcping.bc.TranslatableComponent;

import java.lang.reflect.Type;
import java.util.Arrays;

public class TranslatableComponentSerializer extends BaseComponentSerializer implements JsonSerializer<TranslatableComponent>, JsonDeserializer<TranslatableComponent>
{

    @Override
    public TranslatableComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        TranslatableComponent component = new TranslatableComponent();
        JsonObject object = json.getAsJsonObject();
        deserialize( object, component, context );
        if ( !object.has( "translate" ) )
        {
            throw new JsonParseException( "Could not parse JSON: missing 'translate' property" );
        }
        component.setTranslate( object.get( "translate" ).getAsString() );
        if ( object.has( "with" ) )
        {
            component.setWith( Arrays.asList( context.deserialize( object.get( "with" ), BaseComponent[].class ) ) );
        }
        if ( object.has( "fallback" ) )
        {
            component.setFallback( object.get( "fallback" ).getAsString() );
        }
        return component;
    }

    @Override
    public JsonElement serialize(TranslatableComponent src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject object = new JsonObject();
        serialize( object, src, context );
        object.addProperty( "translate", src.getTranslate() );
        if ( src.getWith() != null )
        {
            object.add( "with", context.serialize( src.getWith() ) );
        }
        if ( src.getFallback() != null )
        {
            object.addProperty( "fallback", src.getFallback() );
        }
        return object;
    }
}
