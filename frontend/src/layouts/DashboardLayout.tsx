import React from 'react';
import { Link, Outlet, useNavigate, useLocation } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { logout } from '../features/auth/authSlice';
import type { RootState } from '../store';
import { 
    HomeIcon, 
    ChartPieIcon, 
    UsersIcon, 
    UserIcon, 
    BuildingOfficeIcon, 
    ArrowLeftOnRectangleIcon,
    BellIcon
} from '@heroicons/react/24/outline';

const navigation = [
    { name: 'Dashboard', href: '/dashboard', icon: HomeIcon },
    { name: 'Pipeline', href: '/pipeline', icon: ChartPieIcon },
    { name: 'Leads', href: '/leads', icon: UserIcon },
    { name: 'Contacts', href: '/contacts', icon: UsersIcon },
    { name: 'Companies', href: '/companies', icon: BuildingOfficeIcon },
];

function classNames(...classes: string[]) {
    return classes.filter(Boolean).join(' ');
}

const DashboardLayout: React.FC = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const location = useLocation();
    const user = useSelector((state: RootState) => state.auth.user);

    const handleLogout = () => {
        dispatch(logout());
        navigate('/login');
    };

    return (
        <div className="min-h-screen flex bg-gray-50 font-sans">
            {/* Premium Sidebar */}
            <div className="hidden lg:fixed lg:inset-y-0 lg:z-50 lg:flex lg:w-72 lg:flex-col">
                <div className="flex grow flex-col gap-y-5 overflow-y-auto bg-gray-900 px-6 pb-4">
                    <div className="flex h-20 shrink-0 items-center">
                        <div className="flex items-center gap-x-3">
                            <div className="bg-indigo-500 rounded-lg p-2">
                                <ChartPieIcon className="h-7 w-7 text-white" />
                            </div>
                            <span className="text-xl font-bold text-white tracking-tight">LeadSphere CRM</span>
                        </div>
                    </div>
                    <nav className="flex flex-1 flex-col">
                        <ul role="list" className="flex flex-1 flex-col gap-y-7">
                            <li>
                                <ul role="list" className="-mx-2 space-y-2">
                                    {navigation.map((item) => (
                                        <li key={item.name}>
                                            <Link
                                                to={item.href}
                                                className={classNames(
                                                    location.pathname === item.href
                                                        ? 'bg-gray-800 text-white'
                                                        : 'text-gray-400 hover:text-white hover:bg-gray-800',
                                                    'group flex gap-x-3 rounded-md p-3 text-sm leading-6 font-medium transition-colors'
                                                )}
                                            >
                                                <item.icon
                                                    className="h-6 w-6 shrink-0"
                                                    aria-hidden="true"
                                                />
                                                {item.name}
                                            </Link>
                                        </li>
                                    ))}
                                </ul>
                            </li>
                            <li className="mt-auto">
                                <button
                                    onClick={handleLogout}
                                    className="group -mx-2 flex w-full gap-x-3 rounded-md p-3 text-sm font-semibold leading-6 text-gray-400 hover:bg-gray-800 hover:text-white transition-colors"
                                >
                                    <ArrowLeftOnRectangleIcon
                                        className="h-6 w-6 shrink-0 text-gray-400 group-hover:text-white"
                                        aria-hidden="true"
                                    />
                                    Log out
                                </button>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

            {/* Main Layout Area */}
            <div className="lg:pl-72 flex flex-1 flex-col h-screen overflow-hidden">
                {/* Premium Top Header */}
                <div className="sticky top-0 z-40 flex h-20 shrink-0 items-center gap-x-4 border-b border-gray-200 bg-white px-4 shadow-sm sm:gap-x-6 sm:px-6 lg:px-8">
                    <div className="flex flex-1 gap-x-4 self-stretch lg:gap-x-6">
                        <div className="relative flex flex-1 items-center">
                            <h2 className="text-2xl font-bold leading-7 text-gray-900 sm:truncate sm:text-3xl sm:tracking-tight capitalize">
                                {location.pathname.split('/').pop() || 'Dashboard'}
                            </h2>
                        </div>
                        <div className="flex items-center gap-x-4 lg:gap-x-6">
                            <button type="button" className="-m-2.5 p-2.5 text-gray-400 hover:text-gray-500">
                                <span className="sr-only">View notifications</span>
                                <BellIcon className="h-6 w-6" aria-hidden="true" />
                            </button>

                            {/* Separator */}
                            <div className="hidden lg:block lg:h-6 lg:w-px lg:bg-gray-200" aria-hidden="true" />

                            {/* Profile dropdown */}
                            <div className="flex items-center gap-x-4">
                                <div className="h-10 w-10 rounded-full bg-indigo-100 flex items-center justify-center border border-indigo-200">
                                    <span className="text-indigo-800 font-bold text-sm">
                                        {user?.firstName?.[0] || 'A'}{user?.lastName?.[0] || 'U'}
                                    </span>
                                </div>
                                <span className="hidden lg:flex lg:items-center">
                                    <span className="text-sm font-semibold leading-6 text-gray-900" aria-hidden="true">
                                        {user?.firstName || 'Admin'} {user?.lastName || 'User'}
                                    </span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>

                <main className="flex-1 overflow-y-auto">
                    <div className="px-4 py-8 sm:px-6 lg:px-8 mx-auto w-full max-w-[96%]">
                        <Outlet />
                    </div>
                </main>
            </div>
        </div>
    );
};

export default DashboardLayout;
