package com.model.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.model.factory.creators.TetrominoCreator;
import com.model.shapes.IShapes;
import com.model.shapes.TetrominosEnum;

public class TetrominoFactory 
{
    static List<TetrominoCreator> creatorsList = new ArrayList<TetrominoCreator>();
    static List<TetrominosEnum> typeList = new ArrayList<>();

    public TetrominoFactory()
    {
        typeList = Arrays.asList(TetrominosEnum.values());

        for (TetrominosEnum type : typeList)
        {
            try
            {
                String creatorName = getClass().getPackageName()  + ".creators." + type.toString() + "TetrominoCreator";
                Class<?> creatorClass = Class.forName(creatorName);
                TetrominoCreator creator = (TetrominoCreator)creatorClass.getDeclaredConstructor().newInstance();
                creatorsList.add(creator);
            }
            catch(ClassNotFoundException | InstantiationException |
                  IllegalAccessException | IllegalArgumentException |
                  InvocationTargetException | NoSuchMethodException | 
                  SecurityException e)
            {
                e.printStackTrace();
            }
            
        }
    }

    public static IShapes create(TetrominosEnum type) 
    {
        int typeID = typeList.indexOf(type);
        TetrominoCreator tetrominoCreator = creatorsList.get(typeID);
        return tetrominoCreator.create();
    }

    public static IShapes getRandomTetromino()
    {
        return create(TetrominosEnum.getRandomType());
    }
}