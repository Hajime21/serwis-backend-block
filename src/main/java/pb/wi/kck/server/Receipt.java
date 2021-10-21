package pb.wi.kck.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor; //?

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Receipt {
    @NonNull private float wartoscParagonu;
    @NonNull String dataZakupow;
    @NonNull String nazwaSklepu;
    private Product[] zakupy;
    private float[] ceny;
}
