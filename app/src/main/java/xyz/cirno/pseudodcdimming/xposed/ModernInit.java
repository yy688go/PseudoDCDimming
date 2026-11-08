package xyz.cirno.pseudodcdimming.xposed;

import androidx.annotation.NonNull;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

public class ModernInit extends XposedModule {

    public ModernInit(@NonNull XposedInterface base, @NonNull ModuleLoadedParam param) {
        super(base, param);
    }

    @Override
    public void onPackageLoaded(@NonNull PackageLoadedParam param) {
        // 在现代 API 102 规范下，系统框架以 "android" 或 "system" 触发加载
        if ("android".equals(param.getPackageName()) || "system".equals(param.getPackageName())) {
            try {
                // 唤起原版的背光拦截与服务初始化
                XposedInit legacy = new XposedInit();
                legacy.initSystem(param.getClassLoader());
            } catch (Throwable t) {
                log("ModernInit 初始化异常: " + t.getMessage());
            }
        }
    }
}
