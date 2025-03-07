package org.apache.cayenne.testdo.testmap.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.property.BaseProperty;
import org.apache.cayenne.exp.property.EntityProperty;
import org.apache.cayenne.exp.property.NumericIdProperty;
import org.apache.cayenne.exp.property.PropertyFactory;
import org.apache.cayenne.exp.property.StringProperty;
import org.apache.cayenne.testdo.testmap.Painting;
import org.apache.cayenne.testdo.testmap.PaintingInfo;

/**
 * Class _PaintingInfo was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _PaintingInfo extends BaseDataObject {

    private static final long serialVersionUID = 1L;

    public static final EntityProperty<PaintingInfo> SELF = PropertyFactory.createSelf(PaintingInfo.class);

    public static final NumericIdProperty<Integer> PAINTING_ID_PK_PROPERTY = PropertyFactory.createNumericId("PAINTING_ID", "PaintingInfo", Integer.class);
    public static final String PAINTING_ID_PK_COLUMN = "PAINTING_ID";

    public static final BaseProperty<byte[]> IMAGE_BLOB = PropertyFactory.createBase("imageBlob", byte[].class);
    public static final StringProperty<String> TEXT_REVIEW = PropertyFactory.createString("textReview", String.class);
    public static final EntityProperty<Painting> PAINTING = PropertyFactory.createEntity("painting", Painting.class);

    protected byte[] imageBlob;
    protected String textReview;

    protected Object painting;

    public void setImageBlob(byte[] imageBlob) {
        beforePropertyWrite("imageBlob", this.imageBlob, imageBlob);
        this.imageBlob = imageBlob;
    }

    public byte[] getImageBlob() {
        beforePropertyRead("imageBlob");
        return this.imageBlob;
    }

    public void setTextReview(String textReview) {
        beforePropertyWrite("textReview", this.textReview, textReview);
        this.textReview = textReview;
    }

    public String getTextReview() {
        beforePropertyRead("textReview");
        return this.textReview;
    }

    public void setPainting(Painting painting) {
        setToOneTarget("painting", painting, true);
    }

    public Painting getPainting() {
        return (Painting)readProperty("painting");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "imageBlob":
                return this.imageBlob;
            case "textReview":
                return this.textReview;
            case "painting":
                return this.painting;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "imageBlob":
                this.imageBlob = (byte[])val;
                break;
            case "textReview":
                this.textReview = (String)val;
                break;
            case "painting":
                this.painting = val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.imageBlob);
        out.writeObject(this.textReview);
        out.writeObject(this.painting);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.imageBlob = (byte[])in.readObject();
        this.textReview = (String)in.readObject();
        this.painting = in.readObject();
    }

}
