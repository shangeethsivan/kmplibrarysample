Pod::Spec.new do |spec|
    spec.name                     = 'SharedLibrary'
    spec.version                  = '0.0.7'
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
                
    spec.script_phases = [
        {
            :name => 'Build SharedLibrary',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end