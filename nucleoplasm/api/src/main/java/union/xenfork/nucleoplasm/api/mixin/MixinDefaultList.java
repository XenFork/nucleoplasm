package union.xenfork.nucleoplasm.api.mixin;

import com.github.artbits.quickio.api.Collection;
import com.github.artbits.quickio.api.DB;
import com.github.artbits.quickio.core.IOEntity;
import com.github.artbits.quickio.core.QuickIO;
import net.minecraft.util.collection.DefaultedList;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import union.xenfork.nucleoplasm.api.quickio.utils.ListEntity;

import java.util.List;

@Mixin(DefaultedList.class)
public abstract class MixinDefaultList<E extends ListEntity> {

    @Shadow @Final @Nullable private E initialElement;
    private final DB db;
    private final int size;
    private Collection<E> collection;


    public MixinDefaultList(String list_name, int size) {
        db = QuickIO.usingDB("list_" + list_name);
        this.size = size;
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "<init>", at = @At("RETURN"))
    private void cInit(List<E> delegate, Object initialElement, CallbackInfo ci) {
        collection = (Collection<E>) db.collection(delegate.get(0).getClass());
    }


    public void add(int index, E element) {
        Validate.notNull(element);
        E one = collection.findOne(e -> e.size == index);
        if (one != null) {
            collection.delete(e -> e.size == index);
        }
        collection.save(element);
    }

    public E set(int index, E element) {
        add(index, element);
        return collection.findOne(e -> e.size == index);
    }

    @NotNull
    public E get(int index) {
        return collection.findOne(e -> e.size == index);
    }

    public int size() {
        return size;
    }
    public void clear() {

        if (initialElement == null) {
            collection.deleteAll();
        } else {
            for(int i = 0; i < this.size(); ++i) {
                this.set(i, this.initialElement);
            }
        }

    }

}
