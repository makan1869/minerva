package ir.serenade.minerva.service;

import ir.serenade.minerva.domain.Activity;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface ActivityService {
    public Activity save(Activity activity);
    public Page<Activity> findPaginated(int page, int size);
    public Page<Activity> findPaginatedByKeyword(Set<String> keyword, int page, int size);

}
