package com.example.courtneyw.tasklist.task.details;

import android.app.Activity;

import com.example.courtneyw.tasklist.dagger.ActivityScope;
import com.example.courtneyw.tasklist.dagger.ComponentBuilder;

import javax.annotation.Nullable;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by courtney.w on 11/8/17.
 */

@ActivityScope
@Subcomponent(modules = TaskDetailFeatureModule.class)
public interface DetailComponent {
    void injectMembers(TaskDetailActivity target);

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<DetailComponent> {
        Builder taskDetailFeature(TaskDetailFeatureModule taskDetailFeatureModule);

        @BindsInstance
        Builder activity(Activity activity);
    }
}