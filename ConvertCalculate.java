
public class ConvertCalculate {
    public static float convertFtoC(final float degree) {
        return (degree - 32) * (5F / 9F);
    }

    public static float convertCtoF(final float degree) {
        return (degree * (9F/5F) + 32);
    }
}