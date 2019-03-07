package org.tasks.intents;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.TaskStackBuilder;
import com.google.common.base.Strings;
import com.todoroo.astrid.activity.MainActivity;
import com.todoroo.astrid.api.Filter;
import com.todoroo.astrid.data.Task;
import org.jetbrains.annotations.Nullable;

public class TaskIntents {

  public static TaskStackBuilder getEditTaskStack(
      Context context, final Filter filter, final long taskId) {
    Intent intent = getTaskListIntent(context, filter);
    intent.putExtra(MainActivity.OPEN_TASK, taskId);
    return TaskStackBuilder.create(context).addNextIntent(intent);
  }

  public static Intent getEditTaskIntent(Context context, Filter filter, Task task) {
    Intent intent = getTaskListIntent(context, filter);
    intent.putExtra(MainActivity.OPEN_NEW_TASK, task);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    return intent;
  }

  public static Intent getTaskListIntent(Context context, @Nullable Filter filter) {
    Intent intent = new Intent(context, MainActivity.class);
    if (filter != null) {
      intent.putExtra(MainActivity.OPEN_FILTER, filter);
    }
    return intent;
  }

  public static Intent getTaskListByIdIntent(Context context, final String filterId) {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.setComponent(new ComponentName(context, MainActivity.class));
    if (!Strings.isNullOrEmpty(filterId)) {
      intent.putExtra(MainActivity.LOAD_FILTER, filterId);
    }
    return intent;
  }
}
