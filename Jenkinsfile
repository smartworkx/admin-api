node {
    // Mark the code checkout 'stage'....
    stage 'Stage Checkout'

    // Checkout code from repository and update any submodules
    checkout scm
   // sh 'git submodule update --init'

    stage 'Stage Build'

    //build your gradle flavor, passes the current build number as a parameter to gradle
    sh "gradle clean assemble -PBUILD_NUMBER=${env.BUILD_NUMBER}"

    stage 'Stage Archive'
    //step([$class: 'ArtifactArchiver', artifacts: 'App/build/libs/*.jar', fingerprint: true])
}
