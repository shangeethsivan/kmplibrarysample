Pod::Spec.new do |spec|
    spec.name                     = 'SharedLibrary'
    spec.version                  = '0.0.11'
    spec.homepage                 = 'https://github.com/shangeethsivan/kmplibrarysample'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = { :type => 'Apache 2.0', :file => 'LICENSE.txt' }
    spec.summary                  = 'A Test Kotlin Multiplatform shared library'
    spec.vendored_frameworks      = 'framework/SharedLibrary.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target    = '14.0'
                
                
    if !Dir.exist?('framework/SharedLibrary.framework') || Dir.empty?('framework/SharedLibrary.framework')
        raise "

        Kotlin framework 'SharedLibrary' doesn't exist yet, so a proper Xcode project can't be generated.
        'pod install' should be executed after running ':generateDummyFramework' Gradle task:

            ./gradlew :library:generateDummyFramework

        Alternatively, proper pod installation is performed during Gradle sync in the IDE (if Podfile location is set)"
    end
                
    spec.xcconfig = {
        'ENABLE_USER_SCRIPT_SANDBOXING' => 'NO',
    }
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':library',
        'PRODUCT_MODULE_NAME' => 'SharedLibrary',
    }
                
end