

import React from 'react'

const Error = ({error,setError}) => {
    const handleError = () => {
        setError("");
    
    }
  return (
    <div class="fixed inset-x-0 top-32 flex items-end justify-right px-4 py-4 justify-end">
    <div
        class="max-w-sm w-full shadow-lg  px-4 py-1 rounded relative bg-red-700 border-l-4 border-blue-700 text-white">
        <div class="p-2">
            <div class="flex items-start">
                <div class="ml-3 w-0 flex-1 pt-0.5">
                    <p class="text-sm leading-5 font-medium">
                        {error}
                    </p>
                </div>
                <div class="ml-4 flex-shrink-0 flex">
                    <button class="inline-flex text-white transition ease-in-out duration-150"
                      onClick={handleError}
                    >
                       <svg class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                         <path fill-rule="evenodd" d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z" clip-rule="evenodd"/>
                       </svg>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
  )
}

export default Error