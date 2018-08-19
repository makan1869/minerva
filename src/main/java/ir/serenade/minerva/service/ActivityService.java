package ir.serenade.minerva.service;

import ir.serenade.minerva.domain.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.Set;

public interface ActivityService {
    public Activity save(Activity activity);
    public DataTablesOutput<Activity> findAll(DataTablesInput input);
    public Page<Activity> findPaginated(int page, int size);
    public Page<Activity> findPaginatedByKeyword(Set<String> keyword, int page, int size);

}
