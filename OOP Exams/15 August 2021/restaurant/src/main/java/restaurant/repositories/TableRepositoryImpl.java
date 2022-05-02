package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableRepositoryImpl implements TableRepository<Table> {
    private Map<Integer, Table> tableMap;

    public TableRepositoryImpl() {
        this.tableMap = new LinkedHashMap<>();
    }

    @Override
    public Table byNumber(int number) {
        return this.tableMap.get(number);
    }

    @Override
    public Collection<Table> getAllEntities() {
        return Collections.unmodifiableCollection(this.tableMap.values());
    }

    @Override
    public void add(Table entity) {
        this.tableMap.put(entity.getTableNumber(), entity);
    }
}
