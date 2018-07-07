package com.oneworldacademymz.owa.room.database.entities;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Database;

import com.oneworldacademymz.owa.room.database.entities.entities.Mensalidade;
import com.oneworldacademymz.owa.room.database.entities.entities.Nota_1a;
import com.oneworldacademymz.owa.room.database.entities.entities.Nota_2a;
import com.oneworldacademymz.owa.room.database.entities.entities.Nota_3a;
import com.oneworldacademymz.owa.room.database.entities.entities.Nota_4a;
import com.oneworldacademymz.owa.room.database.entities.entities.User;


@Database(entities = {User.class, Mensalidade.class, Nota_1a.class, Nota_2a.class, Nota_3a.class, Nota_4a.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract MyDao myDao();

}
