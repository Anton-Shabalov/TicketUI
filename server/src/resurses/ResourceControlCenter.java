package resurses;

import java.util.ResourceBundle;

public class ResourceControlCenter {
    private ResourceBundle mainResourceBundle;
    private ResourceBundle[] resourceBundles;

    public ResourceControlCenter(ResourceBundle mainResourceBundle, ResourceBundle[] resourceBundles) {
        this.mainResourceBundle = mainResourceBundle;
        this.resourceBundles = resourceBundles;
    }

    public ResourceBundle getMainResourceBundle() {
        return mainResourceBundle;
    }

    public ResourceBundle[] getResourceBundles() {
        return resourceBundles;
    }

    public void setIndex(int index) {
        mainResourceBundle = resourceBundles[index];
    }
}
