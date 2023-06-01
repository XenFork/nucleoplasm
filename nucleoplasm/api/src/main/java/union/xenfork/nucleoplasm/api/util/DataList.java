package union.xenfork.nucleoplasm.api.util;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;
import union.xenfork.nucleoplasm.api.quickio.minecraft.item.IItemStack;
import union.xenfork.nucleoplasm.api.quickio.utils.ListEntity;

import java.util.AbstractList;
import java.util.List;

public class DataList<E extends ListEntity> extends AbstractList<E> {
    private final DB db;
    private final Collection<E> collection;
    private final int size;
    private final E initialElement;

    public DataList(String db_name, Class<E> eClass , int size, @Nullable E initialElement) {
        db = QuickIO.usingDB(db_name);
        this.size = size;
        collection = db.collection(eClass);
        this.initialElement = initialElement;
    }

    @Override
    public void add(int index, E element) {
        Validate.notNull(element);
        E one = collection.findOne(e -> e.size == element.size);
        if (one != null) {
            collection.delete(e -> e.size == element.size);
        }
        collection.save(element);
    }

    @Override
    public E set(int index, E element) {
        add(index, element);
        return element;
    }

    public void addE(E e) {
        Validate.notNull(e);
        add(e.size, e);
    }

    public E set(E e) {
        return set(e.size, e);
    }

    @Override
    public E get(int index) {
        E one = collection.findOne(e -> e.size == index);
        if (one != null) {
            return one;
        } else {
            return initialElement;
        }
    }

    /**
     * @param index the index of the element to be removed
     * @return last remove element
     */
    @Override
    public E remove(int index) {
        E one = collection.findOne(e -> e.size == index);
        collection.delete(e -> e.size == index);
        return one;
    }

    @Override
    public int size() {
        return size;
    }

    public List<E> getAll() {
        return collection.findAll();
    }

    @Override
    public void clear() {
        collection.deleteAll();
    }
}
